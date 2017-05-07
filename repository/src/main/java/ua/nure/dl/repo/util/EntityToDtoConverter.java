package ua.nure.dl.repo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EntityToDtoConverter {
    private static final Class<Object> DEFAULT_CONVERTER_KEY = Object.class;

    private Map<Class<?>, EntityToDtoClassConverter> dtoToConverterMap;
    private Map<Class<?>, EntityToDtoClassConverter> entityToConverterMap;

    @Autowired
    protected void setConverters(EntityToDtoClassConverter... converters) {
        for (EntityToDtoClassConverter converter : converters) {
            dtoToConverterMap.put(converter.getDtoClass(), converter);
            entityToConverterMap.put(converter.getEntityClass(), converter);
        }

    }

    public <Destination, Src> Destination convert(Src src, Class<Destination> target) {
        EntityToDtoClassConverter classConverter = getDefaultConverter();

        if (isDto(target)) {
            classConverter = dtoToConverterMap.get(target);
        } else if (isEntity(target)) {
            classConverter = entityToConverterMap.get(target);
        }

        return classConverter.convert(src, target);
    }

    private EntityToDtoClassConverter getDefaultConverter() {
        return dtoToConverterMap.get(DEFAULT_CONVERTER_KEY);
    }


    private boolean isEntity(Class<?> targetClass) {
        return entityToConverterMap.containsKey(targetClass);
    }

    private boolean isDto(Class<?> targetClass) {
        return dtoToConverterMap.containsKey(targetClass);
    }
}
