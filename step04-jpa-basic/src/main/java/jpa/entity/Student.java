package jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
	@Id
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name="sname")
	private String sname;
//	@Column(columnDefinition = "VARCHAR(30) DEFAULT 'SNAME'", insertable = false, updatable = false)
//	@Column(name = "sname", nullable = false, length = 10)
//	private String sname;
	
//	@Column(precision = 10, scale = 2)
//	private BigDecimal price;
	
}