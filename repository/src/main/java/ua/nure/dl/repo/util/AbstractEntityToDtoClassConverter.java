package ua.nure.dl.repo.util;

import java.lang.reflect.Field;

/**
 * @author Bohdan_Suprun
 */
public abstract class AbstractEntityToDtoClassConverter implements EntityToDtoClassConverter {

    protected Class<?> entityClass;
    protected Class<?> dtoClass;

    public AbstractEntityToDtoClassConverter(Class<?> entityClass, Class<?> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public <Src, Des> Des convert(Src ent, Class<Des> desClass) {
        return desClass == getDtoClass()
                ? convertToDto(ent, desClass)
                : convertToEntity(ent, desClass);
    }

    protected abstract <Ent, Dto> Ent convertToEntity(Dto dto, Class<Ent> entClass);

    protected abstract <Ent, Dto> Dto convertToDto(Ent ent, Class<Dto> dtoClass);

    @Override
    public Class<?> getEntityClass() {
        return entityClass;
    }

    @Override
    public Class<?> getDtoClass() {
        return dtoClass;
    }

    protected Object copyFields(Object src, Class<?> target) {
        try {
            Object dest = target.newInstance();
            Field[] destFields = dest.getClass().getDeclaredFields();
            Field[] srcFields = src.getClass().getDeclaredFields();

            for (Field srcField : srcFields) {
                for (Field destField : destFields) {
                    if (isSameFields(srcField, destField)) {
                        destField.setAccessible(true);
                        srcField.setAccessible(true);

                        destField.set(dest, srcField.get(src));
                    }
                }
            }

            return dest;
        } catch (Exception ex) {
            throw new ConverterException("Can't convert class: " + target, ex);
        }
    }

    protected boolean isSameFields(Field src, Field des) {

        if (src.getType() != des.getType()) {
            return false;
        }

        String srcFieldName = src.getName().toLowerCase();
        String desFieldName = des.getName().toLowerCase();

        return srcFieldName.contains(desFieldName) || desFieldName.contains(srcFieldName);

    }
}
