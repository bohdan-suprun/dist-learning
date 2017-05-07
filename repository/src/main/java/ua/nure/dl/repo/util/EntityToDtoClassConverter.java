package ua.nure.dl.repo.util;

/**
 * @author Bohdan_Suprun
 */
public interface EntityToDtoClassConverter {

    <Src, Des> Des convert(Src ent, Class<Des> desClass);

    Class<?> getEntityClass();

    Class<?> getDtoClass();
}
