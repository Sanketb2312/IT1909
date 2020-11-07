package jobblett.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import jobblett.core.JobShift;
import jobblett.core.JobShiftList;

public class JobShiftListDeserializer extends JsonDeserializer<JobShiftList> {

  @Override public JobShiftList deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    ArrayNode arrayNode = (ArrayNode) node.get("jobShifts");
    JobShiftList jobShiftList = new JobShiftList();
    for (JsonNode jobShiftNode : arrayNode) {
      jobShiftList.add(JobblettDeserializer.deserialize(JobShift.class, jobShiftNode));
    }
    return jobShiftList;
  }
}
