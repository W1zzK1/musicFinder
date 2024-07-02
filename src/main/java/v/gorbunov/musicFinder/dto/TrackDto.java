package v.gorbunov.musicFinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;

@Data
@NoArgsConstructor
public class TrackDto {
    private ProviderEnum providerCode;
    private String trackUrl;

    public TrackDto(ProviderEnum providerCode, String trackUrl) {
        this.providerCode = providerCode;
        this.trackUrl = trackUrl;
    }
}
