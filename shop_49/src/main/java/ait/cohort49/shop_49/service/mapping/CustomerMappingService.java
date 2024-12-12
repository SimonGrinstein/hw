package ait.cohort49.shop_49.service.mapping;

import ait.cohort49.shop_49.model.dto.CustomerDTO;
import ait.cohort49.shop_49.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMappingService {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    public Customer mapDtoToEntity(CustomerDTO dto);

    public CustomerDTO mapEntityToDto(Customer entity);

}
