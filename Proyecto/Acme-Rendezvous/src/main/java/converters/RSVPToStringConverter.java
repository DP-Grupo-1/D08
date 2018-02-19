
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.RSVP;


@Transactional
@Component
public class RSVPToStringConverter implements Converter<RSVP, String> {

	@Override
	public String convert(final RSVP rsvp) {
		String result;
		if (rsvp == null)
			result = null;
		else
			result = String.valueOf(rsvp.getId());

		return result;
	}

}
