package br.com.gems.exception.dto;

import br.com.gems.exception.enums.ErrorTypeEnum;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponseDTO {

    private LocalDateTime occurenceTime;
    private ErrorTypeEnum errorType;
    private String message;
    private String path;
    private String method;

    @Override
    public String toString() {
        return new StringBuilder()
                .append( "An error occurred at: " )
                .append( this.occurenceTime )
                .append( " with status: " )
                .append( this.errorType.name() )
                .append( " and message: " )
                .append( this.message )
                .append( " in path: " )
                .append( this.path )
                .append( " with method: " )
                .append( this.method )
                .toString();
    }

}
