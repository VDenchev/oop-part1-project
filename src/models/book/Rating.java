package models.book;

public enum Rating {
    ZERO_STARS("zero stars", "zero", "0", "0/5"),
    ONE_STAR("one star", "one", "1", "1/5"),
    TWO_STARS("two stars", "two", "2", "2/5"),
    THREE_STARS("three stars","three","3","3/5"),
    FOUR_STARS("four stars", "four", "4", "4/5"),
    FIVE_STARS("five stars", "five", "5", "5/5");

    private String[] descriptionList;

    Rating(String... descriptionList) {
        this.descriptionList = descriptionList;
    }

    public static Rating getByDescription(String description) {
        if(description != null) {
            for (Rating rating : Rating.values()) {
                for (String desc : rating.descriptionList) {
                    if (desc.equalsIgnoreCase(description)) {
                        return rating;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Invalid rating value!");
    }

    @Override
    public String toString() {
        return descriptionList[3];
    }
}
