
public class TransactionImp implements Transaction {

	public TransactionImp() {
	}

	@Override
	public void sign(byte[] pvk) {
	}

	@Override
	public boolean isSignatureValid() {
		return false;
	}

	@Override
	public byte[] toBytes() {
		return null;
	}

	@Override
	public void setSender(byte[] sender) {
	}

	@Override
	public void setReceiver(byte[] receiver) {
	}

	@Override
	public void setAmount(int amount) {
	}

	@Override
	public void setSignature(byte[] signature) {
	}

	@Override
	public byte[] getSender() {
		return null;
	}

	@Override
	public byte[] getReceiver() {
		return null;
	}

	@Override
	public int getAmount() {
		return 0;
	}

	@Override
	public byte[] getSignature() {
		return null;
	}

}
