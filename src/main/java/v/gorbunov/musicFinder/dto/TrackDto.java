package v.gorbunov.musicFinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private String providerCode;
    private String trackUrl;
}
