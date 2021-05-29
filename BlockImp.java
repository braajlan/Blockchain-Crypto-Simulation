public class BlockImp implements Block {

	public BlockImp() {
	}

	@Override
	public void setMiner(byte[] miner) {
	}

	@Override
	public void setNonce(int nonce) {
	}

	@Override
	public void setTransaction(Transaction transaction) {
	}

	@Override
	public void setPrevHash(byte[] prevHash) {
	}

	@Override
	public void setHash(byte[] hash) {
	}

	@Override
	public byte[] getMiner() {
		return null;
	}

	@Override
	public int getNonce() {
		return 0;
	}

	@Override
	public byte[] getHash() {
		return null;
	}

	@Override
	public byte[] getPrevHash() {
		return null;
	}

	@Override
	public Transaction getTransaction() {
		return null;
	}

	@Override
	public void compHash() {
	}

	@Override
	public boolean isHashValid() {
		return false;
	}

	@Override
	public void mine() {
	}

	@Override
	public byte[] toBytes() {
		return null;
	}

}
