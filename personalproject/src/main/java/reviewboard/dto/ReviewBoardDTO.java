package reviewboard.dto;

import java.util.Date;

public class ReviewBoardDTO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private int stars;
	private int cafe_num;
	private String cafe_name;
	private Date reg_date;
	private int read_count;
	private int good_count;
	private int group_num;
	private int re_step;
	private int re_level;
	private String filename;
	private int filesize;
	private int download;
	private String show_op;
	private String ip;
	private int comment_count; //DB에 없음
	private String ext; //DB에 없음
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer=writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public int getGood_count() {
		return good_count;
	}
	public void setGood_count(int good_count) {
		this.good_count = good_count;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public String getshow_op() {
		return show_op;
	}
	public void setshow_op(String show_op) {
		this.show_op = show_op;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public int getCafe_num() {
		return cafe_num;
	}
	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}
	public ReviewBoardDTO() {
	}
	@Override
	public String toString() {
		return "ReviewBoardDTO [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", stars=" + stars + ", cafe_num=" + cafe_num + ", reg_date=" + reg_date + ", read_count="
				+ read_count + ", good_count=" + good_count + ", group_num=" + group_num + ", re_step=" + re_step
				+ ", re_level=" + re_level + ", filename=" + filename + ", filesize=" + filesize + ", download="
				+ download + ", show_op=" + show_op + ", ip=" + ip + ", comment_count=" + comment_count + ", ext=" + ext
				+ "]";
	}
	public ReviewBoardDTO(int num, String writer, String title, String content, int stars, int cafe_num,
			Date reg_date, int read_count, int good_count, int group_num, int re_step, int re_level, String filename,
			int filesize, int download, String show_op, String ip, int comment_count, String ext) {
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.stars = stars;
		this.cafe_num = cafe_num;
		this.reg_date = reg_date;
		this.read_count = read_count;
		this.good_count = good_count;
		this.group_num = group_num;
		this.re_step = re_step;
		this.re_level = re_level;
		this.filename = filename;
		this.filesize = filesize;
		this.download = download;
		this.show_op = show_op;
		this.ip = ip;
		this.comment_count = comment_count;
		this.ext = ext;
	}
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}

	
	
	
}
