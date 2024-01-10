package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;


public class AdminConverter {
    private AdminConverter(){}
    public static Administration convert(AdminEntity admin){
        return Administration.builder()
                .id(admin.getId())
                .fullName(admin.getFullName())
                .dob(admin.getDob())
                .address(admin.getAddress())
                .build();
    }
}
