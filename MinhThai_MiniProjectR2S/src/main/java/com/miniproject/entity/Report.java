package com.miniproject.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.miniproject.entity.Report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Report {
@Id
private Serializable group;
private double revenue;
private long quantity;
}
