package cafe.dto;

public class CafeDTO {
	private String cafe_name;
	private String cafe_name_sub;
	private String menu;
	private String price;
	private String open_date;
	private String tel;
	private String address;
	private String parking;
	private String opening_hours;
	private String instagram;
	private String website;
	private String img_src;
	private String review_main;
	private String review_sub;
	private String addr_map;
	private String short_review; 
	private int stars_count;
	private int stars_tot;
	private int stars_avg;
	private int cafe_num;
	
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOpen_date() {
		return open_date;
	}
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getImg_src() {
		return img_src;
	}
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}
	public String getReview_main() {
		return review_main;
	}
	public void setReview_main(String review_main) {
		this.review_main = review_main;
	}
	public String getReview_sub() {
		return review_sub;
	}
	public void setReview_sub(String review_sub) {
		this.review_sub = review_sub;
	}
	public String getAddr_map() {
		return addr_map;
	}
	public void setAddr_map(String addr_map) {
		this.addr_map = addr_map;
	}
	public String getShort_review() {
		return short_review;
	}
	public void setShort_review(String short_review) {
		this.short_review = short_review;
	}
	public int getStars_count() {
		return stars_count;
	}
	public void setStars_count(int stars_count) {
		this.stars_count = stars_count;
	}
	public int getStars_tot() {
		return stars_tot;
	}
	public void setStars_tot(int stars_tot) {
		this.stars_tot = stars_tot;
	}
	public int getStars_avg() {
		return stars_avg;
	}
	public void setStars_avg(int stars_avg) {
		this.stars_avg = stars_avg;
	}
	
	public int getCafe_num() {
		return cafe_num;
	}
	public void setCafe_num(int cafe_num) {
		this.cafe_num = cafe_num;
	}
	public String getCafe_name_sub() {
		return cafe_name_sub;
	}
	public void setCafe_name_sub(String cafe_name_sub) {
		this.cafe_name_sub = cafe_name_sub;
	}

	@Override
	public String toString() {
		return "CafeDTO [cafe_name=" + cafe_name + ", cafe_name_sub=" + cafe_name_sub + ", menu=" + menu + ", price="
				+ price + ", open_date=" + open_date + ", tel=" + tel + ", address=" + address + ", parking=" + parking
				+ ", opening_hours=" + opening_hours + ", instagram=" + instagram + ", website=" + website
				+ ", img_src=" + img_src + ", review_main=" + review_main + ", review_sub=" + review_sub + ", addr_map="
				+ addr_map + ", short_review=" + short_review + ", stars_count=" + stars_count + ", stars_tot="
				+ stars_tot + ", stars_avg=" + stars_avg + ", cafe_num=" + cafe_num + "]";
	}
	public CafeDTO(String cafe_name, String cafe_name_sub, String menu, String price, String open_date, String tel,
			String address, String parking, String opening_hours, String instagram, String website, String img_src,
			String review_main, String review_sub, String addr_map, String short_review, int stars_count, int stars_tot,
			int stars_avg, int cafe_num) {
		this.cafe_name = cafe_name;
		this.cafe_name_sub = cafe_name_sub;
		this.menu = menu;
		this.price = price;
		this.open_date = open_date;
		this.tel = tel;
		this.address = address;
		this.parking = parking;
		this.opening_hours = opening_hours;
		this.instagram = instagram;
		this.website = website;
		this.img_src = img_src;
		this.review_main = review_main;
		this.review_sub = review_sub;
		this.addr_map = addr_map;
		this.short_review = short_review;
		this.stars_count = stars_count;
		this.stars_tot = stars_tot;
		this.stars_avg = stars_avg;
		this.cafe_num = cafe_num;
	}

	public CafeDTO() {
	}
}
