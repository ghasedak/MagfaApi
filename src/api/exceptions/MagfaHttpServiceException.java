package api.exceptions;

import api.assets.ErrorCodes;

/**
 * Created by gladiator on 1/24/16.
 */
public class MagfaHttpServiceException extends RuntimeException {
    public MagfaHttpServiceException(){super();}

    //Make exception by error message;
    public MagfaHttpServiceException(String message){super(message);}

    //Make exception by error code;
    public MagfaHttpServiceException(int errorCode){
        super(ErrorCodes.getDescriptionForCode(errorCode));
    }
}
