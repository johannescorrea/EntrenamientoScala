package co.com.intergrupo.dao;

import javax.persistence.EntityManager;

import co.com.intergrupo.dao.generics.AbstratDao;
import co.com.intergrupo.dto.PruebaDto;

public class PruebaDao extends AbstratDao<PruebaDto> {

  public PruebaDao() {
    super(PruebaDto.class);
  }

  public PruebaDao(EntityManager entityManager) {
    super(PruebaDto.class, entityManager);
  }

}
