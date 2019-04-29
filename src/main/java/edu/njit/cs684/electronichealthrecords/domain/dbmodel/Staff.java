package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Staff {

    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull(message = "StaffId is not sent in request")
    private String staffId;
    @NotNull(message = "StaffType is not sent in request")
    private String staffType;

    @NotNull(message = "staff account is not sent in request")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Staff() {
    }

    public Staff(String staffId, String staffType) {
        this.staffId = staffId;
        this.staffType = staffType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
}
