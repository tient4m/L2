package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lc_province")
@Getter
@Setter
public class Province {
    @Id
    @Column(name = "province_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "code")
    private String code;

    @Column(name = "dcsr")
    private String description;
}

/*
 *      POJO: Plain Old Java Object, là những Java Object mà chúng không bị hạn chế bởi bất kỳ thứ gì khác. Chúng không hiện
 * thực hay được mở rộng từ bất kỳ class nào khác và chúng cũng ko chứa bất kỳ annotation nào trong bản thân chúng.
 * Chúng chỉ chứa các thuộc tính, setter và getter và có thể thêm các phương thức Override toString() và equals()
 *      @Entity: Nếu @Entity không có tham số, tên của entity này trong DB sẽ là tên của class
 *      @Id: Mỗi Entity đều phải có một id, id dùng để định danh, phân biệt giữa các bản ghi với nhau, đã là id thì nó phải
 * unique. Id được dùng làm khóa chính của bảng.
 *      @Entity(name = 'EntityName'): dùng để chỉ tên của entity được Hibernate quản lý.
 *      @Table(name = 'TableName'): chỉ đích danh tên của table dưới DB.
 */
