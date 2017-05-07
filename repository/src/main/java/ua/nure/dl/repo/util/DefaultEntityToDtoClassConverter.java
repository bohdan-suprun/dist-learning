package ua.nure.dl.repo.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Bohdan_Suprun
 */
@Component
public class DefaultEntityToDtoClassConverter extends AbstractEntityToDtoClassConverter {

    public DefaultEntityToDtoClassConverter() {
        super(Object.class, Object.class);
    }

    @Override
    protected <Ent, Dto> Dto convertToDto(Ent ent, Class<Dto> dtoClass) {
        return (Dto) copyFields(ent, dtoClass);
    }

    @Override
    protected <Ent, Dto> Ent convertToEntity(Dto dto, Class<Ent> entClass) {
        return (Ent) copyFields(dto, entClass);
    }
}
