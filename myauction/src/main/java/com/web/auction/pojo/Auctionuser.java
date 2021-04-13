package com.web.auction.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Auctionuser implements Serializable {
    /**
     * 用户表
     *
     * @mbg.generated
     */
    private Integer userid;
    @Size(min = 6, max = 16, message = "{register.username.length.error}")
    private String username;
    @Size(min = 5, message = "{register.password.length.error}")
    private String userpassword;
    @Pattern(regexp = "\\d{18}", message = "{register.usercardno.format.error}")
    private String usercardno;

    private String usertel;

    private String useraddress;

    private String userpostnumber;

    private Integer userisadmin;

    private String userquestion;

    private String useranswer;

    private static final long serialVersionUID = 1L;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsercardno() {
        return usercardno;
    }

    public void setUsercardno(String usercardno) {
        this.usercardno = usercardno;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserpostnumber() {
        return userpostnumber;
    }

    public void setUserpostnumber(String userpostnumber) {
        this.userpostnumber = userpostnumber;
    }

    public Integer getUserisadmin() {
        return userisadmin;
    }

    public void setUserisadmin(Integer userisadmin) {
        this.userisadmin = userisadmin;
    }

    public String getUserquestion() {
        return userquestion;
    }

    public void setUserquestion(String userquestion) {
        this.userquestion = userquestion;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", userpassword=").append(userpassword);
        sb.append(", usercardno=").append(usercardno);
        sb.append(", usertel=").append(usertel);
        sb.append(", useraddress=").append(useraddress);
        sb.append(", userpostnumber=").append(userpostnumber);
        sb.append(", userisadmin=").append(userisadmin);
        sb.append(", userquestion=").append(userquestion);
        sb.append(", useranswer=").append(useranswer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}