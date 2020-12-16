package com.matsu.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeightData  {
	public float weight;
	public String date;
	public WeightData(ResultSet rs){
		try {
			this.weight = rs.getFloat("weight");
			this.date = String.valueOf(rs.getDate("date"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
