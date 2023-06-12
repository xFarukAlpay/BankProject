package Accounts;

//import java.time.LocalDate;
//import java.time.Period;

public class CurrentAccount extends Account
{

	public  CurrentAccount(int id) {
		// TODO Auto-generated constructor stub
		super.setAccountId(id);
		super.type = AccountType.CURRENT;
	}

	
	@Override
	public void accountRules() 
	{
		// TODO Auto-generated method stub
		System.out.println("Hesap faizsiz ve para olma zorunlulugu yoktur.");
	}


	@Override
	public float benefit() 
	{
		// TODO Auto-generated method stub
		 /*Hesap faizsizdir*/
		return 0.0F;
	}

}
