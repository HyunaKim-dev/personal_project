package common;

public class CafePager {
	public static final int PAGE_SCALE=6;
	public static final int BLOCK_SCALE=10;
	private int curPage;
	private int prevPage;
	private int nextPage;
	private int totPage;
	private int totBlock;
	private int curBlock;
	private int prevBlock;
	private int nextBlock;
	private int pageBegin;
	private int pageEnd;
	private int blockStartPage;
	private int blockEndPage;
	
	public CafePager(int count, int curPage) {
		this.curPage=curPage;
		curBlock=1;
		setTotPage(count);
		setPageRange();
		setTotBlock();
		setBlockRange();
	}
	
	public CafePager() {};
	
	public void setBlockRange() {
		curBlock=(curPage-1)/BLOCK_SCALE+1; //
		blockStartPage=(curBlock-1) * BLOCK_SCALE +1;
		blockEndPage=blockStartPage + (BLOCK_SCALE-1);
		if(blockEndPage > totPage) {
			blockEndPage=totPage;
		}
		prevPage= curBlock==1 ? 1 : (curBlock-1)*BLOCK_SCALE; //[이전]: 현재블록이 1인 경우를 제외하고 이전 블록의 마지막 페이지로 이동
		nextPage=curBlock>totBlock ?
				(curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1; //[다음] 현재 1번째 블록이면 다음 블록의 첫번졔 페이지로 이동
		if(nextPage >= totPage) nextPage=totPage;
	}
	
	public void setPageRange() {
		pageBegin = (curPage-1) * PAGE_SCALE+1; //3페이지는 21번 게시글부터
		pageEnd = pageBegin + (PAGE_SCALE-1);
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {
		totPage = (int)Math.ceil(count * 1.0 / PAGE_SCALE); //count: 레코드 수
	}

	public int getTotBlock() {
		return totBlock;
	}

	public void setTotBlock() {
		totBlock = (int)Math.ceil(totPage * 1.0 / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockStartPage() {
		return blockStartPage;
	}

	public void setBlockStartPage(int blockStartPage) {
		this.blockStartPage = blockStartPage;
	}

	public int getBlockEndPage() {
		return blockEndPage;
	}

	public void setBlockEndPage(int blockEndPage) {
		this.blockEndPage = blockEndPage;
	}

	public static int getPageScale() {
		return PAGE_SCALE;
	}

	public static int getBlockScale() {
		return BLOCK_SCALE;
	}
	
	
	
}
