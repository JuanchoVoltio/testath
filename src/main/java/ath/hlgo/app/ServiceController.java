package ath.hlgo.app;

import ath.hlgo.business.ServiceBusiness;
import ath.hlgo.model.User;
import ath.hlgo.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "User Admin Services")
public class ServiceController {

    @Autowired
    ServiceBusiness serviceBusiness;

    @RequestMapping(value = "/findUser", method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(  value = "Find user in database",
            response = String.class)
    @ApiResponses( {
            @ApiResponse(code = 200, message = "OK",  response = User.class),
            @ApiResponse(code = 400, message = "Bad Request",  response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error",  response = String.class)
    } )
    @GetMapping("/response-entity-builder-with-http-headers")
    public ResponseEntity<String> getSubscriber(@ApiParam(required = true, value = "User's id. Must be bigger than 0")
                                                 @RequestParam String userId){

        ResponseEntity<String> result;
        User businessResult;
        HttpHeaders responseHeaders = new HttpHeaders();

        System.out.println("Starting operation....");

        try {
            businessResult = serviceBusiness.performUserSearch(Integer.parseInt(userId));
            result = (businessResult.getId() > 0) ?
                        ResponseEntity.ok().headers(responseHeaders).body(businessResult.toJson().toString())
                        : ResponseEntity.ok().headers(responseHeaders).body("{\"answer\":\""+ Constants.getErrorMessage(businessResult.getId()) +"\"}");

            System.out.println("Operation OK.");
        }catch (Exception ex){
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body(ex.getMessage());
            System.out.println(ex.getMessage());
        }

        return result;
    }

}
