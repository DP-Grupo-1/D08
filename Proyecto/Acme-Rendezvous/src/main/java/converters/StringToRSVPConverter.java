
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.RSVPRepository;
import domain.RSVP;

@Transactional
@Component
public class StringToRSVPConverter implements Converter<String, RSVP> {

	@Autowired
	RSVPRepository	rsvpRepository;


	@Override
	public RSVP convert(final String text) {
		RSVP result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.rsvpRepository.findOne(id);
			}
		} catch (Throwable error) {
			throw new IllegalArgumentException(error);
		}

		return result;
	}

}
