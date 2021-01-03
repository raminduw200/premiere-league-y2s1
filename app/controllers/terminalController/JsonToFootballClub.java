package controllers.terminalController;

import entities.DefaultFootballClub;
import entities.SchoolFootballClub;
import entities.UniversityFootballClub;
import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonToFootballClub implements JsonDeserializer {
    // Deserialize array of objects of abstract and non-abstract classes
    @Override
    public Object deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if(jsonObject.has("uniName")){
            return jsonDeserializationContext.deserialize(jsonObject, UniversityFootballClub.class);
        } else if (jsonObject.has("schoolName")){
            return jsonDeserializationContext.deserialize(jsonObject, SchoolFootballClub.class);
        } else {
            return jsonDeserializationContext.deserialize(jsonObject, DefaultFootballClub.class);
        }
    }

}
