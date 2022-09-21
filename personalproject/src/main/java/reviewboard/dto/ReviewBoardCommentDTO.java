package reviewboard.dto;

import java.util.Date;

public class ReviewBoardCommentDTO {
	int comment_num;
	int reviewboard_num;
	String writer;
	String content;
	Date reg_date;
	int group_num;
	int re_step;
	int re_level;
	String show_op;
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getReviewboard_num() {
		return reviewboard_num;
	}
	public void setReviewboard_num(int reviewboard_num) {
		this.reviewboard_num = reviewboard_num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getGroup_num() {
		return group_num;
	}
	public void setGroup_num(int group_num) {
		this.group_num = group_num;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) { 
		this.re_level = re_level;
	}
	public String getShow() {
		return show_op;
	}
	public void setShow(String show_op) {
		this.show_op = show_op;
	}
	@Override
	public String toString() {
		return "ReviewBoardCommentDTO [comment_num=" + comment_num + ", reviewboard_num=" + reviewboard_num
				+ ", writer=" + writer + ", content=" + content + ", reg_date=" + reg_date + ", good_count="
				+ ", group_num=" + group_num + ", re_step=" + re_step + ", re_level=" + re_level
				+ ", show=" + show_op + "]";
	}
	public ReviewBoardCommentDTO(int comment_num, int reviewboard_num, String writer, String content, Date reg_date,
			int good_count, int group_num, int re_step, int re_level, String show_op) {
		this.comment_num = comment_num;
		this.reviewboard_num = reviewboard_num;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.group_num = group_num;
		this.re_step = re_step;
		this.re_level = re_level;
		this.show_op = show_op;
	}
	public ReviewBoardCommentDTO() {
	}
	
	
}
