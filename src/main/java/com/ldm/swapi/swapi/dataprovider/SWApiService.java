package com.ldm.swapi.swapi.dataprovider;

import com.ldm.swapi.infra.apiconsumer.DataType;
import com.ldm.swapi.infra.apiconsumer.LDMApiConsumerImpl;
import com.ldm.swapi.infra.apiconsumer.ParseSpecField;
import com.ldm.swapi.infra.apiconsumer.SWEntity;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SWApiService {

    static protected final String BASE_URL = "https://swapi.dev/api/";

    private final LDMApiConsumerImpl ldmApiConsumer;

    public SWApiService() {

        ldmApiConsumer = new LDMApiConsumerImpl(BASE_URL);
    }

    public List<SWEntity> getAllSync(
            String uri, String resultFieldName, Class elementClass, List<ParseSpecField> parseSpecFields)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        Object data = ldmApiConsumer.getDataSync(uri);
        List<SWEntity> swEntities = new ArrayList<SWEntity>();
        List lhmFilmResults = (List) ((LinkedHashMap) data).get(resultFieldName);

        try {

            for (Object item : lhmFilmResults) {

                LinkedHashMap lhmItem = (LinkedHashMap) item;
                SWEntity swEntity = (SWEntity) elementClass.getDeclaredConstructor().newInstance();

                for (ParseSpecField psf : parseSpecFields) {

                    String oldName = psf.getSourceField();
                    String newName = psf.getTargetField();
                    DataType dataType = psf.getDataType();

                    Field field = swEntity.getClass().getDeclaredField(newName);

                    if (!ObjectUtils.isEmpty(field)) {
                        field.setAccessible(true);
                        field.set(swEntity, lhmItem.get(oldName));
                    }
                }

                swEntities.add(swEntity);
            }

        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {

            throw e;
        }

        return swEntities;
    }

    public SWEntity getOneSync(
            String uri, Class elementClass, List<ParseSpecField> parseSpecFields)
            throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        Object data = ldmApiConsumer.getDataSync(uri);
        LinkedHashMap lhmFilmResult = (LinkedHashMap) data;
        SWEntity swEntity;

        try {

            swEntity = (SWEntity) elementClass.getDeclaredConstructor().newInstance();

            for (ParseSpecField psf : parseSpecFields) {

                String oldName = psf.getSourceField();
                String newName = psf.getTargetField();
                DataType dataType = psf.getDataType();

                Field field = swEntity.getClass().getDeclaredField(newName);

                if (!ObjectUtils.isEmpty(field)) {
                    field.setAccessible(true);
                    field.set(swEntity, lhmFilmResult.get(oldName));
                }
            }

        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {

            throw e;
        }

        return swEntity;
    }
}
