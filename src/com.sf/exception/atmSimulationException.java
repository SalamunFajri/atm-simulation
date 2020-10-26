package com.sf.exception;


public class atmSimulationException extends Exception
{

    private String		errorCode		= null;	// this will hold system defined error code
    private Object[]	errorParameters	= null;	// this will hold parameters for error code/message

    public atmSimulationException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    public atmSimulationException(String message)
    {
        super(message);
    }

    public atmSimulationException(Throwable throwable)
    {
        super(throwable);
    }

    public atmSimulationException(String errorCode, String message, Object[] errorParameters)
    {
        super(message);
        this.setErrorCode(errorCode);
        this.setErrorParameters(errorParameters);
    }

    public atmSimulationException(String errorCode, String message, Throwable throwable)
    {
        super(message, throwable);
        this.setErrorCode(errorCode);
    }

    public atmSimulationException(String errorCode, String message, Object[] errorParameters, Throwable throwable)
    {
        super(message, throwable);
        this.setErrorCode(errorCode);
        this.setErrorParameters(errorParameters);
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public Object[] getErrorParameters()
    {
        return errorParameters;
    }

    public void setErrorParameters(Object[] errorParameters)
    {
        this.errorParameters = errorParameters;
    }
}
