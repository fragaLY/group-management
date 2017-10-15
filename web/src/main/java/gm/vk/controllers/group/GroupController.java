package gm.vk.controllers.group;

import gm.vk.core.dto.group.GroupDto;
import gm.vk.service.group.GroupService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/groups")
@Validated
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<GroupDto>> getGroups() {
        final List<GroupDto> groups = groupService.findAll();
        return new ResponseEntity<>(groups, new HttpHeaders(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GroupDto> getGroup(@Range(min = 1) @PathVariable("id") final Integer id) {
        final GroupDto group = groupService.findOne(id);
        return new ResponseEntity<>(group, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGroup(@Valid @RequestBody final GroupDto group) {

        final GroupDto savedGroup = groupService.save(group);

        final URI createdGroupUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedGroup.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(createdGroupUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editGroup(@Valid @RequestBody final GroupDto group) {

        final GroupDto savedGroup = groupService.save(group);

        final URI editedGroupUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedGroup.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(editedGroupUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GroupDto> deleteGroup(@Valid @RequestBody final GroupDto group) {
        groupService.delete(group);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GroupDto> deleteGroup(
            @Range(min = 1) @PathVariable("id") final Integer id) {
        groupService.delete(id);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
