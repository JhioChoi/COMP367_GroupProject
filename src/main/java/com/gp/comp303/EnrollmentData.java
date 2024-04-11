package com.gp.comp303;

public class EnrollmentData {
    private Long studentId;
    private Long programCode;
    private Double amountPaid;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProgramCode() {
        return programCode;
    }

    public void setProgramCode(Long programCode) {
        this.programCode = programCode;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}

