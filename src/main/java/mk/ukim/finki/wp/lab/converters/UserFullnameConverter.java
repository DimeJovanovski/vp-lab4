package mk.ukim.finki.wp.lab.converters;

import mk.ukim.finki.wp.lab.model.UserFullname;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        if (userFullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (userFullname.getSurname() != null && !userFullname.getSurname()
                .isEmpty()) {
            sb.append(userFullname.getSurname());
        }

        if (userFullname.getName() != null
                && !userFullname.getName().isEmpty()) {
            sb.append(SEPARATOR);
            sb.append(userFullname.getName());
        }

        return sb.toString();
    }

    @Override
    public UserFullname convertToEntityAttribute(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return null;
        }

        String[] pieces = fullName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserFullname personName = new UserFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (fullName.contains(SEPARATOR)) {
            personName.setName(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                personName.setSurname(pieces[1]);
            }
        } else {
            personName.setName(firstPiece);
        }

        return personName;
    }
}
