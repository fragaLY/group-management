package gm.vk.controllers.favicon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(value = "favicon", description = "Favicon API")
@RequestMapping("favicon.ico")
public class FaviconController {

  private static final String FORWARD_FAVICON = "forward:/resources/images/favicon.ico";

  @GetMapping
  @ApiOperation(
    value = "Retrieves favicon",
    notes = "The path of favicon will be sent in the location response",
    response = String.class
  )
  public String getFavicon() {
    return FORWARD_FAVICON;
  }
}
