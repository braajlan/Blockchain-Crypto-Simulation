public class BlockchainImp implements Blockchain {
	public BlockchainImp() {
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public List<Block> getBlocks() {
		return null;
	}

	@Override
	public int getBalance(byte[] pbk) {
		return 0;
	}

	@Override
	public void removeInvalid() {
	}

	@Override
	public boolean addBlock(Block b) {
		return false;
	}

	@Override
	public byte[] getLastBlockHash() {
		return null;
	}
}
