package com.bewitchment.api.capability.magicpower;

public class MagicPowerCapability
{
	int amount, max_amount, bonus_amount;
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public int getMaxAmount()
	{
		return max_amount;
	}
	
	public void setMaxAmount(int max_amount)
	{
		this.max_amount = max_amount;
	}
	
	public int getBonusAmount()
	{
		return max_amount;
	}
	
	public void setBonusAmount(int bonus_amount)
	{
		this.bonus_amount = bonus_amount;
	}
}