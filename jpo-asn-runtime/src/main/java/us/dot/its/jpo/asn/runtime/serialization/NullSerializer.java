package us.dot.its.jpo.asn.runtime.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import us.dot.its.jpo.asn.runtime.types.Asn1Null;

/**
 * Serializer for ASN.1 NULL Types.
 * <p>Refer to
 * <ul>
 *   <li>JER: ITU-T Rec. X.697 (02/2021), section 26: "The encoding of the null value shall be the
 *   JSON token null"</li>
 *   <li>XER: ITU-T Rec. X.680 (02/2021), sec. 24.3: defines XMLNullValue as 'empty'"</li>
 * </ul>
 */
public class NullSerializer extends StdSerializer<Asn1Null> {

  protected NullSerializer() {
    super(Asn1Null.class);
  }

  @Override
  public void serialize(Asn1Null asn1Null, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    if (jsonGenerator instanceof ToXmlGenerator xmlGenerator) {
      // XML: write empty element
      xmlGenerator.writeStartObject();
      xmlGenerator.writeEndObject();
    } else {
      // JER: write 'null'
      jsonGenerator.writeNull();
    }
  }
}
