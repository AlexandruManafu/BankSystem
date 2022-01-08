package ro.uvt.dp.exceptions;

public class ClientsLimitExceeded extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientsLimitExceeded (String message)
	{
		super(message);
	}
}
