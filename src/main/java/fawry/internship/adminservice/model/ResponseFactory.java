package fawry.internship.adminservice.model;

public class ResponseFactory {

    public static ResponseEnvelop getSuccessResponse()
    {
        ResponseEnvelop response = new ResponseEnvelop();
        response.setSuccess(true);
        return response;
    }

    public static ResponseEnvelop getFailureResponse()
    {
        ResponseEnvelop response = new ResponseEnvelop();
        response.setSuccess(false);
        return response;
    }
}
