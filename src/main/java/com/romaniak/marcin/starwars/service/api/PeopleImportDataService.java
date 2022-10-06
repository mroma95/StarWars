package com.romaniak.marcin.starwars.service.api;

import com.romaniak.marcin.starwars.dto.PeopleDto;
import com.romaniak.marcin.starwars.exception.IllegalUserActionException;
import com.romaniak.marcin.starwars.mapper.PeopleMapper;
import com.romaniak.marcin.starwars.repository.PeopleRepository;
import com.romaniak.marcin.starwars.utility.UrlConstants;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class PeopleImportDataService extends AbstractImportDataService<PeopleDto> {

  private final PeopleRepository peopleRepository;
  private static final Set<Long> importedPeoples = new HashSet<>();

  @Override
  protected PeopleDto getResponseFromApi(RestTemplate rt, String finalUrl) {
    rt.getForObject(finalUrl, Object.class);
    return rt.getForObject(finalUrl, PeopleDto.class);
  }

  @Override
  protected void save(PeopleDto dto) {
    peopleRepository.save(PeopleMapper.INSTANCE.toEntity(dto));
  }

  @Override
  protected void validateCanAdd(Long id) {
    if (importedPeoples.contains(id)){
      throw new IllegalUserActionException("You added this person, you can't add duplicate!");
    }
  }

  @Override
  protected String getUrl() {
    return UrlConstants.PEOPLE_URL;
  }

  @Override
  public Set<Long> getImportedCollection(){
    return importedPeoples;
  }

}