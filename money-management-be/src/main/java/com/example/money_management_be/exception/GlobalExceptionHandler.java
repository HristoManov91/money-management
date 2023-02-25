package com.example.money_management_be.exception;

import static java.util.Objects.nonNull;

import com.example.money_management_be.exception.apiError.ApiError;
import com.example.money_management_be.exception.apiError.ApiSubError;
import com.example.money_management_be.exception.exceptionClasses.ResourceAlreadyExistException;
import com.example.money_management_be.exception.exceptionClasses.ResourceInvalidParameterException;
import com.example.money_management_be.exception.exceptionClasses.ResourceNotFoundException;
import com.example.money_management_be.exception.exceptionClasses.ResourceUndeletableException;
import com.example.money_management_be.exception.exceptionClasses.ValidationException;
import com.example.money_management_be.exception.messages.ErrorMessages;
import java.nio.file.AccessDeniedException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE + 50)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String LANG_HEADER = "Lang";
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        String localizedMessage = getLocalizedMessage(ErrorMessages.SERVLET_REQUEST_PARAMETER_EXCEPTION, new Object[]{ex.getParameterName()}, request);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, localizedMessage, request);
        return buildResponseEntity(apiError);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        String supportedMediaTypes = ex.getSupportedMediaTypes().stream().map(x -> x.toString()).collect(Collectors.joining(", "));
        String localizedMessage = getLocalizedMessage(ErrorMessages.MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION, new Object[]{ex.getContentType(), supportedMediaTypes},
            request);
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, localizedMessage, request);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        String localizedMessage = getLocalizedMessage(ErrorMessages.MESSAGE_NOT_WRITABLE_EXCEPTION, new Object[]{}, request);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, localizedMessage, request);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle NoHandlerFoundException.
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request);
        String localizedMessage = getLocalizedMessage(ErrorMessages.HANDLER_NOT_FOUND_EXCEPTION, new Object[]{ex.getHttpMethod(), ex.getRequestURL()}, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle javax.persistence.EntityNotFoundException
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, request);
        String localizedMessage = getLocalizedMessage(ErrorMessages.UNEXPECTED_EXCEPTION, new Object[]{}, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
        WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getCause();
            String constraintMessage = "exception.data.integrity.violation." +
                (constraintViolationException.getConstraintName() == null ? "" : constraintViolationException.getConstraintName());
            String localizedMessage = getLocalizedMessage(constraintMessage, new Object[]{}, request);
            if (constraintMessage.equals(localizedMessage)) {
                localizedMessage = getLocalizedMessage("exception.data.integrity.violation", new Object[]{
                    constraintViolationException.getMessage(),
                    constraintViolationException.getConstraintName()
                }, request);
            }
            ApiError apiError = new ApiError(HttpStatus.CONFLICT, localizedMessage, request);
            return buildResponseEntity(apiError);
        }
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request);
        String localizedMessage = getLocalizedMessage(ErrorMessages.UNEXPECTED_EXCEPTION, new Object[]{}, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
        WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request);
        String localizedMessage =
            getLocalizedMessage(ErrorMessages.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION, new Object[]{
                ex.getName(),
                ex.getValue(),
                ex.getRequiredType().getSimpleName()
            }, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExist(EntityExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        log.error(ex.getMessage());

        Comparator<ApiSubError> comparator = Comparator.comparing(ApiSubError::getField)
            .thenComparing(ApiSubError::getMessage);

        List<ApiSubError> apiSubErrors = ex.getErrors().getFieldErrors().stream()
            .map(err -> new ApiSubError(err.getField(), err.getDefaultMessage()))
            .sorted(comparator)
            .collect(Collectors.toList());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request);
        apiError.setMessage(ex.getMessage());
        apiError.setSubErrors(apiSubErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, request);
        String localizedMessage = getLocalizedMessage(ErrorMessages.ACCESS_DENIED_EXCEPTION, null, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceInvalidParameterException.class)
    protected ResponseEntity<Object> handleEntityInvalidParameter(ResourceInvalidParameterException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.httpStatus(), request);
        String localizedMessage = getLocalizedMessage(ex.getMessageId(), ex.getArgs(), request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.httpStatus(), request);
        String localizedMessage = getLocalizedMessage(ex.getMessageId(), ex.getArgs(), request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExist(ResourceAlreadyExistException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.httpStatus(), request);
        String localizedMessage = getLocalizedMessage(ex.getMessageId(), ex.getArgs(), request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceUndeletableException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExist(ResourceUndeletableException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.httpStatus(), request);
        String localizedMessage = getLocalizedMessage(ex.getMessageId(), ex.getArgs(), request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
        (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(status, request);

        Comparator<ApiSubError> comparator = Comparator.comparing(ApiSubError::getField)
            .thenComparing(ApiSubError::getMessage);

        List<ApiSubError> allErrors =
            ex.getAllErrors().stream().map(err -> {
                    String localizedMessage = getLocalizedMessage(err.getCode(), err.getArguments(), request);
                    if (err instanceof FieldError) {
                        return new ApiSubError(((FieldError) err).getField(), localizedMessage);
                    } else {
                        return new ApiSubError(Objects.requireNonNull(err.getArguments())[1].toString(), localizedMessage);
                    }
                })
                .sorted(comparator)
                .toList();

        apiError.setSubErrors(allErrors);
        apiError.setMessage(Objects.requireNonNull(ex.getLocalizedMessage()));
        return buildResponseEntity(apiError);

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getLocalizedMessage(String messageId, Object[] args, WebRequest request) {
        String lang = request.getHeader(LANG_HEADER);
        Locale locale = Locale.forLanguageTag(nonNull(lang) ? lang : "bg");

        String res;
        try {
            if (args == null) {
                args = new Object[]{};
            }
            res = messageSource.getMessage(messageId, convertArgs(args, locale), locale);
        } catch (NoSuchMessageException e) {
            res = messageId;
        }
        return res;
    }

    private Object[] convertArgs(Object[] args, Locale locale) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];

            if (arg instanceof Map) {
                Map<String, ?> map = (Map<String, ?>) arg;
                for (String k : map.keySet()) {
                    args[i] = String.format("{%s = %s}", k, map.get(k));
                }
            }

            if (arg instanceof String) {
                String value = arg.toString();
                args[i] = messageSource.getMessage(value, null, locale);
            }
        }

        return args;
    }

    /**
     * Handle javax.persistence.OptimisticLockException
     */
    @ExceptionHandler(javax.persistence.OptimisticLockException.class)
    protected ResponseEntity<Object> handleOptimisticLock(javax.persistence.OptimisticLockException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, request);
        String localizedMessage = getLocalizedMessage(ErrorMessages.OPTIMISTIC_LOCK_EXCEPTION, new Object[]{}, request);
        apiError.setMessage(localizedMessage);
        return buildResponseEntity(apiError);
    }
}
