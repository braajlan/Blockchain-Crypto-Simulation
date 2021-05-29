public class BlockchainImp implements Blockchain {
	List<Block> chain;
	private int index;

	public BlockchainImp() {
		chain = new LinkedList<Block>();
	}

	@Override
	public int length() {
		int counter = 0;
		if(chain.empty()){
			return 0;
		}
		chain.findFirst();
		while(!chain.last()){
			counter++;
			chain.findNext();
		}
		counter++;
		return counter;
	}

	@Override
	public List<Block> getBlocks() {
		return chain;
	}

	@Override
	public int getBalance(byte[] pbk) {
		int total = 0;
		index = 0;
		if(chain.empty()) {
			System.out.println("chain is empty");
			return 0;
		} else {
			chain.findFirst();
			if(chain.retrieve().getMiner() == pbk) {
				total += minerReward;
			}
			if(chain.retrieve().getTransaction() != null) {
				if(chain.retrieve().getTransaction().getReceiver() == pbk) {
					total+= chain.retrieve().getTransaction().getAmount();
				}
				if(chain.retrieve().getTransaction().getSender() == pbk) {
					total-= chain.retrieve().getTransaction().getAmount();
					if(total <0) {
						return -1;
					}
				}
			}
			if(this.length() == 1) {
				return total;
			}
		}
		chain.findFirst();
		chain.findNext();
		index++;
		while(!chain.last()) { //other elements
			if(chain.retrieve().getMiner() == pbk) {
				total+= minerReward;
			}
			if(chain.retrieve().getTransaction().getReceiver() == pbk) {
				total+= chain.retrieve().getTransaction().getAmount();
			}
			if(chain.retrieve().getTransaction().getSender() == pbk) {
				total-= chain.retrieve().getTransaction().getAmount();
				if(total <0) {
					return -1;
				}
			}
			chain.findNext();
			index++;
		}
		if(chain.retrieve().getMiner() == pbk) {
			total+= minerReward;
		}

		if(pbk == chain.retrieve().getTransaction().getReceiver()) {
			total+= chain.retrieve().getTransaction().getAmount();
		}
		if(pbk == chain.retrieve().getTransaction().getSender()) {
			total-= chain.retrieve().getTransaction().getAmount();
			if(total<0){
				return -1;
			}
		}
		return total;
	}

	@Override
	public void removeInvalid() {
		if(chain.empty()) {
			System.out.println("chain is empty"); /**remove this**/
			return;
		}
		chain.findFirst(); //first element case 
		if(chain.retrieve().getTransaction() == null) {
			if(!Utils.validBlockHash(chain.retrieve().getHash())) { ///////////replaced with isHash function
				while(!chain.last()) {
					chain.remove();
				} chain.remove();
			}
			if(length() <= 1)
				return;
		} else { //other elements
			chain.findFirst();
			if(!chain.retrieve().isHashValid() || !chain.retrieve().getTransaction().isSignatureValid()) { ///////////
				while(!chain.last()) {
					chain.remove();
				} chain.remove();
			}
			if(length() <= 1) {
				return;
			}
		} 
		chain.findFirst();
		chain.findNext();

		while(!chain.last()) {
			byte[] temp = chain.retrieve().getTransaction().getSender(); //local variable
			if(!chain.retrieve().isHashValid() || !chain.retrieve().getTransaction().isSignatureValid()) {
				while(!chain.last()){
					chain.remove();
				} chain.remove();
				break;
			}  if(this.getBalance(temp) == -1) {
				chain.findFirst();
				for(int i = 1;i<index;i++) {
					chain.findNext();
				} while(!chain.last()) {
					chain.remove();
				} chain.remove();
				break;
			}
			chain.findNext();
			}
	}

	@Override
	public boolean addBlock(Block b) {
		Block newBlock = b;
		if(chain.empty()) {
			newBlock.setPrevHash(initHash);
			chain.insert(newBlock);
			return true;
		}
		if(b.getTransaction() == null || b.getPrevHash() == initHash) {
			return false;
		}
			newBlock.setPrevHash(getLastBlockHash());
			chain.insert(newBlock);
			return true;
	}

	@Override
	public byte[] getLastBlockHash() {
		if(chain.empty()) {
			return null;
		} else {
			chain.findFirst();
			while(!chain.last()){
				chain.findNext();
			}
			return chain.retrieve().getHash();
		}
	}
}