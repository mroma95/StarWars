package com.romaniak.marcin.starwars.service.api;

import com.romaniak.marcin.starwars.dto.ApiDto;
import com.romaniak.marcin.starwars.exception.IllegalUserActionException;
import com.romaniak.marcin.starwars.service.api.interfaces.ImportData;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
@Service
public abstract class AbstractImportDataService<T extends ApiDto> implements ImportData {

  public void importData(Long id) {
    validateCanAdd(id);
    RestTemplate rt = new RestTemplate();
    String finalUrl = getUrl() + id;
    try {
      T dto = getResponseFromApi(rt, finalUrl);
      save(dto);
      log.info("Saved object with name : {}", dto.getName());
      addIdToCollection(id);
    } catch (HttpClientErrorException e) {
      throw new IllegalUserActionException(
          String.format("Object with id = %d, doesn't exist in external API", id));
    }
  }

  private void addIdToCollection(Long id) {
    getImportedCollection().add(id);
  }

  protected abstract Set<Long> getImportedCollection();

  protected abstract T getResponseFromApi(RestTemplate rt, String finalUrl);

  protected abstract void save(T dto);

  protected abstract void validateCanAdd(Long id);

  protected abstract String getUrl();
}