
public class TransactionImp implements Transaction {
	private KPair sender;
	private KPair receiver;
	private int amount;
	private byte[] signature;
	
	public TransactionImp() {
		sender = Utils.getKeyPair();
		receiver = Utils.getKeyPair();
		amount = 0;
		signature  = null;
	}

	@Override
	public void sign(byte[] pvk) {
		if(pvk != getSender()){return;}
		byte[] a = Utils.concat(getSender(), getReceiver());
		byte[] b = Utils.toBytes(amount);
		byte[] input = Utils.concat(a, b);
		signature = Utils.sign(input, pvk);
	}

	@Override
	public boolean isSignatureValid() { //////////
		byte[] a = Utils.concat(getSender(), getReceiver());
		byte[] b = Utils.toBytes(amount);
		byte[] input = Utils.concat(a, b);
		boolean valid = Utils.isSignatueValid(input,signature,getSender()); System.out.println(valid); // Prints true
		input [0]++; // Change input
		valid = Utils.isSignatueValid(input,signature,getSender()); System.out.println(valid); // Prints false
		return valid;
		}

	@Override
	public byte[] toBytes() { ///// another solution (Utils.toByte)
		byte[] a = Utils.concat(getSender(), getReceiver());
		byte[] b = Utils.toBytes(amount);
		byte[] c = Utils.concat(a, b);
		byte[] input = Utils.concat(c, signature);
		
		return input;
	}

	@Override
	public void setSender(byte[] sender) {
		this.sender.publicKey = sender;
	}

	@Override
	public void setReceiver(byte[] receiver) {
		this.receiver.publicKey = receiver;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	@Override
	public byte[] getSender() {
		return sender.publicKey;
	}

	@Override
	public byte[] getReceiver() {
		return receiver.publicKey;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public byte[] getSignature() {
		return signature;
	}
}
