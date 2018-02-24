package co.com.education.exceptions;

public class TechnicalException extends RuntimeException {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public TechnicalException(String msg) {
      super(msg);
   }

   public TechnicalException(String msg, Throwable t) {
      super(msg, t);
   }
}
