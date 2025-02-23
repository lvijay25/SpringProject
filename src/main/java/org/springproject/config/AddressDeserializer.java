package org.springproject.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springproject.entity.Address;

import java.io.IOException;

public class AddressDeserializer extends JsonDeserializer<Address> {

    @Override
    public Address deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String addressString = jsonParser.getText();
        String[] addressArray = addressString.split(",");
        if(addressArray.length != 3){
            throw new JsonProcessingException("Invalid address format"){};
        }
        return new Address(addressArray[0], addressArray[1], addressArray[2]);
    }
}
