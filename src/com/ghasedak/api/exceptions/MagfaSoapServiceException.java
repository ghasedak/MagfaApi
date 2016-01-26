package com.ghasedak.api.exceptions;

import com.ghasedak.api.assets.ErrorCodes;

/**
 * Created by gladiator on 1/24/16.
 */
public class MagfaSoapServiceException extends RuntimeException {
    public MagfaSoapServiceException(){super();}

    public MagfaSoapServiceException(String message){super(message);}

    public MagfaSoapServiceException(int errorCode){super(ErrorCodes.getDescriptionForCode(errorCode));}
}
