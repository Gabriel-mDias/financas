package br.com.gems.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ModelMapperUtils {

    public static void ignoreLazyFieldsNotInitialized(ModelMapper modelMapper ) {
        modelMapper.getConfiguration()
                .setPropertyCondition( context -> !isAEntity( context ) || !isAEntityNotInitialized( context.getSource() ) );
    }

    private boolean isAEntity( Object source ) {
        return source instanceof PersistentCollection;
    }

    private boolean isAEntityNotInitialized( Object source ) {
        return isAEntity(source) && !((PersistentCollection)source).wasInitialized();
    }

}
