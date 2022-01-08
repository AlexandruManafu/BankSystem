package ro.uvt.dp.exceptions;

public class DuplicateIBAN extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateIBAN (String message)
	{
		super(message);
	}
}
