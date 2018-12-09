pragma solidity ^0.5.0;

// File: contracts/commons/Signable.sol
contract Signable {

    event AddedSigner(address signer);

    address internal signer;

    /**
      @dev Setup function sets initial storage of contract.
      @param _signer List of signer.
    */
    function initSigner(address _signer) internal {
        require(_signer != address(0), "Invalid owner address provided, signer address cannot be null");
        signer = _signer;
        emit AddedSigner(signer);
    }

    function isSigner(address _signer) internal view returns (bool) {
        return signer == _signer;
    }

}

// File: contracts/commons/SignatureDeserializer.sol

library SignatureDeserializer {

    /**
      @dev Recovers address who signed the message
      @param _hash operation ethereum signed message hash
      @param _signature message `hash` signature
    */
    function recoverKey (
        bytes32 _hash,
        bytes memory _signature
    ) internal pure returns (address) {
        bytes32 r;
        bytes32 s;
        uint8 v;

        assembly {
            r := mload(add(_signature, 32))
            s := mload(add(_signature, 64))
            v := and(mload(add(_signature, 65)), 255)
        }

        if (v < 27) {
            v += 27;
        }

        return ecrecover(_hash, v, r, s);
    }

}

// File: contracts/core/MarmoCore.sol

contract MarmoCore is Signable {

    mapping(bytes32 => address) public relayerOf;
    mapping(bytes32 => bool) public isCanceled;

    event Relayed(
        bytes32 _id,
        bytes32[] _dependencies,
        address _to,
        uint256 _value,
        bytes _data,
        bytes32 _salt,
        address _relayer,
        bool _success
    );
    event Canceled(
        bytes32 _id
    );

    function setup(address _signer) public {
        initSigner(_signer);
    }

    function encodeTransactionData(
        bytes32[] memory _dependencies,
        address _to,
        uint256 _value,
        bytes memory _data,
        uint256 _minGasLimit,
        uint256 _maxGasPrice,
        bytes32 _salt
    ) public view returns (bytes32) {
        return keccak256(
            abi.encodePacked(
                this,
                keccak256(abi.encodePacked(_dependencies)),
                _to,
                _value,
                keccak256(_data),
                _minGasLimit,
                _maxGasPrice,
                _salt
            )
        );
    }

    function dependenciesSatisfied(bytes32[] memory _dependencies) internal view returns (bool) {
        for (uint256 i; i < _dependencies.length; i++) {
            if (relayerOf[_dependencies[i]] == address(0)) return false;
        }

        return true;
    }

    function relay(
        bytes32[] memory _dependencies,
        address _to,
        uint256 _value,
        bytes memory _data,
        uint256 _minGasLimit,
        uint256 _maxGasPrice,
        bytes32 _salt,
        bytes memory _signature
    ) public returns (
        bool success,
        bytes memory data
    ) {
        bytes32 hashTransaction = encodeTransactionData(_dependencies, _to, _value, _data, _minGasLimit, _maxGasPrice, _salt);

        require(tx.gasprice <= _maxGasPrice);
        require(!isCanceled[hashTransaction], "Transaction was canceled");
        require(relayerOf[hashTransaction] == address(0), "Transaction already relayed");
        require(dependenciesSatisfied(_dependencies), "Parent relay not found");
        validateHashTransaction(hashTransaction, _signature);

        require(gasleft() > _minGasLimit);
        (success, data) = _to.call.value(_value)(_data);

        relayerOf[hashTransaction] = msg.sender;

        emit Relayed(
            hashTransaction,
            _dependencies,
            _to,
            _value,
            _data,
            _salt,
            msg.sender,
            success
        );
    }

    function cancel(bytes32 _hashTransaction) external {
        require(msg.sender == address(this), "Only wallet can cancel txs");
        require(relayerOf[_hashTransaction] == address(0), "Transaction was already relayed");
        isCanceled[_hashTransaction] = true;
    }

    function validateHashTransaction(bytes32 transactionHash, bytes memory signature) internal view {
        address currentSigner = SignatureDeserializer.recoverKey(transactionHash, signature);
        require(isSigner(currentSigner), "Signature not provided by signer");
    }

    function() external payable {}

}