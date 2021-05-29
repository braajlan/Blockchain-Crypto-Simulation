public class BlockImp implements Block {
	private int nonce;
	private byte[] miner;
	private Transaction transaction;
	private byte[] prevHash;
	private byte[] hash;
	
	public BlockImp() {
		nonce = 0;
		miner = null;
		transaction = null;
		prevHash = null;
		hash = null;
	}
	
	private Transaction initTransaction() {
		transaction.setAmount(0);
		transaction.setReceiver(null);
		transaction.setSender(null);
		transaction.setSignature(null);
		
		return transaction;
	}
	
	@Override
	public void setMiner(byte[] miner) {
		this.miner = miner;
	}

	@Override
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	@Override
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public void setPrevHash(byte[] prevHash) {
		this.prevHash = prevHash;
	}

	@Override
	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	@Override
	public byte[] getMiner() {
		return miner;
	}

	@Override
	public int getNonce() {
		return nonce;
	}

	@Override
	public byte[] getHash() {
		return hash;
	}

	@Override
	public byte[] getPrevHash() {
		return prevHash;
	}

	@Override
	public Transaction getTransaction() {
		return transaction;
	}

	@Override
	public void compHash() {
		byte[] a = Utils.toBytes(getNonce());
		byte[] b = transaction.toBytes();
		byte[] c = Utils.concat(a, b);
		byte[] input = Utils.concat(c, prevHash);
		hash = Utils.getHash(input);
	}

	@Override
	public boolean isHashValid() {
		compHash();
		return Utils.validBlockHash(hash);
	}

	@Override
	public void mine() { //////////////////////////////////
		if(getTransaction() == null){
			if(getPrevHash() == Blockchain.initHash){ //firstBlock
				initTransaction();
				} else {
					return;
				}
		}
		nonce = 0;
		while(!Utils.validBlockHash(hash)){
			nonce++;
			compHash();
		}
	}

	@Override
	public byte[] toBytes() {
		byte[] a = Utils.toBytes(getNonce());
		byte[] b = transaction.toBytes();
		byte[] c = Utils.concat(a, b);
		byte[] input = Utils.concat(c, prevHash);
		return input;
	}

}
