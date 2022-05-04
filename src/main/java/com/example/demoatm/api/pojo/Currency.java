package com.example.demoatm.api.pojo;

import lombok.Value;

import java.util.List;

@Value
public class Currency{
	List<CurrencyItem> currency;
}